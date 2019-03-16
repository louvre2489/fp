package com.louvre2489.fp.domain

import com.louvre2489.fp.domain.systemcharacteristics.GSC
import com.louvre2489.fp.domain.value.{ AdjustedFunctionPoint, UnadjustedFunctionPoint }

case class TargetSystem(gsc: GSC = GSC(), functions: Functions, cfp: UnadjustedFunctionPoint) {

  def DFP(cfp: UnadjustedFunctionPoint): AdjustedFunctionPoint = {
    val sumUFP = functions.values.foldLeft(UnadjustedFunctionPoint(0))((ufp, f) => f.UFP + ufp)
    (sumUFP + cfp) * gsc.VAF
  }

  def EFP(cfp: UnadjustedFunctionPoint, oldGSC: GSC): AdjustedFunctionPoint = AdjustedFunctionPoint(0)

}
