package com.louvre2489.fp.domain

import com.louvre2489.fp.domain.entity.{ GSC, _ }
import com.louvre2489.fp.domain.value._

case class TargetSystem(systemInfo: SystemInfo, functions: Functions = Functions(Nil)) {

  /**
    * UFP is the points that is not adjusted by GSC
    * @return
    */
  def UFP: UnadjustedFunctionPoint = UFP(_ => true)

  def UFP(filter: entity.Function => Boolean): UnadjustedFunctionPoint =
    functions.values
      .filter(filter)
      .foldLeft(UnadjustedFunctionPoint(0))((ufp, f) => f.UFP + ufp)

  /**
    * DFP is the points of the anew development.
    * DFP consists of the points of the system functions and the data migration functions.
    * @return
    */
  def DFP: AdjustedFunctionPoint = UFP * systemInfo.gsc.VAF

  /**
    * EFP is the points of the improve development.
    * EFP consists of the points of the below:
    *   1) Additional functions
    *   2) Changing functions
    *   3) Delete functions
    *   4) Migration functions
    * @param oldGSC oldGSC is the characteristics before improvement
    * @return
    */
  def EFP(oldGSC: GSC): AdjustedFunctionPoint = {

    // add functions
    val addUFP = UFP(f => f.developmentType == ADD)

    // change functions
    val changeUFP = UFP(f => f.developmentType == CHGA)

    // data transfer functions
    val dataTransferUFP = UFP(f => f.isDataMigrationFunction.value)

    // delete functions
    val deleteUFP = UFP(f => f.developmentType == DEL)

    ((addUFP + changeUFP + dataTransferUFP) * oldGSC.VAF) + (deleteUFP * systemInfo.gsc.VAF)
  }

  /**
    * function points of the anew develop application
    * @return
    */
  def initialAFP(): AdjustedFunctionPoint = UFP(f => !f.isDataMigrationFunction.value) * systemInfo.gsc.VAF

  /**
    * function points of the improve develop application
    * @param oldFunctions oldFunctions is the functions before improvemnt
    * @param oldGSC oldGSC is the characteristics before improvement
    * @return
    */
  def improveAFP(oldFunctions: Functions, oldGSC: GSC): AdjustedFunctionPoint = {

    val addFilter: entity.Function => Boolean    = f => f.developmentType == ADD
    val changeFilter: entity.Function => Boolean = f => f.developmentType == CHGA
    val deleteFilter: entity.Function => Boolean = f => f.developmentType == DEL

    // add functions
    val addUFP = UFP(addFilter)

    // change functions
    val changeUFP = UFP(changeFilter)

    // delete functions
    val deleteUFP = UFP(deleteFilter)

    // old add functions
    val oldAddUFP = oldFunctions.values
      .filter(addFilter)
      .foldLeft(UnadjustedFunctionPoint(0))((ufp, f) => f.UFP + ufp)

    // old change functions
    val oldChangeUFP = oldFunctions.values
      .filter(changeFilter)
      .foldLeft(UnadjustedFunctionPoint(0))((ufp, f) => f.UFP + ufp)

    // old delete functions
    val oldADeleteUFP = oldFunctions.values
      .filter(deleteFilter)
      .foldLeft(UnadjustedFunctionPoint(0))((ufp, f) => f.UFP + ufp)

    val UFPBeforeImprovement = oldAddUFP + oldChangeUFP + oldADeleteUFP

    ((addUFP + changeUFP + UFPBeforeImprovement) - (deleteUFP + oldChangeUFP)) * systemInfo.gsc.VAF
  }
}
