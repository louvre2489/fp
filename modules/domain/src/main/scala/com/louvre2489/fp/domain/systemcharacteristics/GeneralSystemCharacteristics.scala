package com.louvre2489.fp.domain.systemcharacteristics
import com.louvre2489.fp.domain.value.{ DI, ValueAdjustmentFactor }

case class GSC(
    dataCommunications: DataCommunications = DataCommunications(DI(0)),
    distributedDataProcessing: DistributedDataProcessing = DistributedDataProcessing(DI(0)),
    performance: Performance = Performance(DI(0)),
    heavilyUsedConfiguration: HeavilyUsedConfiguration = HeavilyUsedConfiguration(DI(0)),
    transactionRate: TransactionRate = TransactionRate(DI(0)),
    onlineDataEntry: OnlineDataEntry = OnlineDataEntry(DI(0)),
    endUserEffeciency: EndUserEffeciency = EndUserEffeciency(DI(0)),
    onlineUpdate: OnlineUpdate = OnlineUpdate(DI(0)),
    complexProcessing: ComplexProcessing = ComplexProcessing(DI(0)),
    reUsability: ReUsability = ReUsability(DI(0)),
    installationEase: InstallationEase = InstallationEase(DI(0)),
    operationalEase: OperationalEase = OperationalEase(DI(0)),
    multipleSites: MultipleSites = MultipleSites(DI(0)),
    facilitateChange: FacilitateChange = FacilitateChange(DI(0))
) {

  /**
    * Create new GSC object.
    * Set new General System Characteristic,
    * then this method returns new GSC object.
    * @param gc General System Charactistic that you want to change
    * @return new GSC
    */
  def changeDI(gc: GeneralSystemCharacteristic): GSC = {

    var dataCommunications        = this.dataCommunications
    var distributedDataProcessing = this.distributedDataProcessing
    var performance               = this.performance
    var heavilyUsedConfiguration  = this.heavilyUsedConfiguration
    var transactionRate           = this.transactionRate
    var onlineDataEntry           = this.onlineDataEntry
    var endUserEffeciency         = this.endUserEffeciency
    var onlineUpdate              = this.onlineUpdate
    var complexProcessing         = this.complexProcessing
    var reUsability               = this.reUsability
    var installationEase          = this.installationEase
    var operationalEase           = this.operationalEase
    var multipleSites             = this.multipleSites
    var facilitateChange          = this.facilitateChange

    gc match {
      case gc: DataCommunications => dataCommunications = gc

      case gc: DistributedDataProcessing => distributedDataProcessing = gc

      case gc: Performance => performance = gc

      case gc: HeavilyUsedConfiguration => heavilyUsedConfiguration = gc

      case gc: TransactionRate => transactionRate = gc

      case gc: OnlineDataEntry => onlineDataEntry = gc

      case gc: EndUserEffeciency => endUserEffeciency = gc

      case gc: OnlineUpdate => onlineUpdate = gc

      case gc: ComplexProcessing => complexProcessing = gc

      case gc: ReUsability => reUsability = gc

      case gc: InstallationEase => installationEase = gc

      case gc: OperationalEase => operationalEase = gc

      case gc: MultipleSites => multipleSites = gc

      case gc: FacilitateChange => facilitateChange = gc
    }

    GSC(
      dataCommunications,
      distributedDataProcessing,
      performance,
      heavilyUsedConfiguration,
      transactionRate,
      onlineDataEntry,
      endUserEffeciency,
      onlineUpdate,
      complexProcessing,
      reUsability,
      installationEase,
      operationalEase,
      multipleSites,
      facilitateChange
    )
  }

  def TDI: DI = {
    dataCommunications.di +
    distributedDataProcessing.di +
    performance.di +
    heavilyUsedConfiguration.di +
    transactionRate.di +
    onlineDataEntry.di +
    endUserEffeciency.di +
    onlineUpdate.di +
    complexProcessing.di +
    reUsability.di +
    installationEase.di +
    operationalEase.di +
    multipleSites.di +
    facilitateChange.di
  }

  def VAF: ValueAdjustmentFactor = ValueAdjustmentFactor((TDI.value * 0.01) + 0.65)
}

sealed trait GeneralSystemCharacteristic {

  val di: DI

  val MIN_POINT = 0

  val MAX_POINT = 5
}

/**
  * データ通信
  * @param di
  * 0 バッチ処理のみ、又はスタンドアローンＰＣで稼動
  * 1 バッチ処理であるが、リモートでの入力又はリモートでの印刷有
  * 2 バッチ処理であるが、リモートでの入力とはリモートでの印刷の両方を持つ
  * 3 オンラインでデータ収集するか、バッチ処理の為のＴＰ（遠隔操作）フロンとエンド又は照会システム
  * 4 フロントエンド以上の通信機能を持つが、1種類のＴＰ通信プロトコルだけをサポートする
  * 5 フロントエンド以上の通信機能を持つが、複数種類のＴＰ通信プロトコルをサポートする
  */
case class DataCommunications(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 分散処理
  * @param di
  * 0 システムの構成要素間でのデータ転送又はデータ処理機能について関与しない
  * 1 ＰＣの表計算やＤＢＭＳなどのようなシステムの他の構成要素で、エンドユーザコンピューティング（ＥＵＣ）の為のデータを準備する
  * 2 ＥＵＣの為でなく、データが転送の為に準備され、システムの他の構成要素に転送され、処理される。
  * 3 一方向のみのオンラインでの分散処理とデータ転送がある。
  * 4 双方向のオンラインでの分散処理とデータ転送がある。
  * 5 データ処理がシステムの最適な構成要素上で動的に実行される。
  */
case class DistributedDataProcessing(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 性能
  * @param di
  * 0 ユーザからの性能に対する特別な要求は明言されていない
  * 1 性能条件と設計について要求が明言され、レビューが行われたが、特別な対応は要求されなかった
  * 2 レスポンスタイムまたはスループットがピーク時に厳しい状況となるが、
  *   CPU使用率に対する設計上の特別な配慮は不要である。処理の期限は翌営業日である
  * 3 レスポンスタイムまたはスループットが全業務時間中厳しい状況となるが、
  *   CPU使用に対する設計上の特別な配慮は不要である。インタフェースをとっているシステムによって、処理の期限に制限がある
  * 4 3に加えて、ユーザの指定した性能要件が厳しいため、設計段階で性能分析が必要である
  * 5 4に加えて、ユーザの指定した性能要件を満たすために、設計、開発、導入の段階で性能分析ツールを使用する必要がある
  */
case class Performance(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 高負荷構成
  * @param di
  * 0 運用上の制約は明示されていないし、暗黙的にもない
  * 1 運用上の制約はあるが、通常よりも厳しくない。制約を満たすための特別の配慮は不要である
  * 2 セキュリティまたはタイミング上の配慮が必要である
  * 3 アプリケーションの特定部分に対して、特別なプロセッサ要件がある
  * 4 指定された運用上の制約によって、セン卜ラルプロセッサまたは専用プロセッサに関して特別の制約が必要である
  * 5 4に加えて、システムの分散構成要素に関してアプリケーションに特別な制約が必要である
  */
case class HeavilyUsedConfiguration(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * トランザクション量
  * @param di
  * 0 トランザクションのピーク期間はないと予測される
  * 1 トランザクションのピーク期間は、毎月、四半期、季節ごと、毎年１回などの程度で予想される
  * 2 毎週トランザクションのピーク期間があると予想される
  * 3 毎日トランザクションのピーク期間があると予想される
  * 4 アプリケーションに対するユーザ要求、サービス契約の面で
  *   ユーザから高いトランザクション処理率が要求されているため、設計段階で性能分析作業が必要である
  * 5 4に加えて、設計、開発、導入の段階で性能分析ツールを使用する必要がある
  */
case class TransactionRate(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * オンライン入力
  * @param di
  * 0 すべてのトランザクションがバッチモードで処理される
  * 1 トランザクションの1〜7%がオンライン画面経由で入力される
  * 2 トランザクションの8〜15%がオンライン画面経由で入力される
  * 3 トランザクションの16〜23%がオンライン画面経由で入力される
  * 4 トランザクションの24〜30%がオンライン画面経由で入力される
  * 5 トランザクションの30%以上がオンライン画面経由で入力される
  */
case class OnlineDataEntry(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * エンドユーザー効率
  * @param di
  * 0 機能のいずれも実現していない
  * 1 機能の1〜3項目を実現している
  * 2 機能の4〜5項目を実現している
  * 3 機能の6項目以上を実現している。
  *   しかし操作効率に関してユーザからの要求は特にない
  * 4 機能の6項目以上を実現している。
  *   さらに操作効率に対するユーザ要求が厳しいため、ヒューマンファクターに対する設計作業が必要である
  *   (たとえは、キー操作を最少にする、デフォルトを最大に活用する、テンプレートを使用するなど)
  * 5 4に加えて、専用のツールおよび処理を用いて目的が達成されたことを証明する必要がある
  */
case class EndUserEffeciency(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  *オンライン更新
  * @param di
  * 0 オンライン更新は無い
  * 1 1〜3のILFをオンラインで更新する。更新量は少なく、回復は容易である
  * 2 4以上のILFをオンラインで更新する。更新量は少なく、回復は容易である
  * 3 主要なILFをオンラインで更新する
  * 4 3に加えて、データ損失保護が必須であり、特別の設計、プログラムがされている
  * 5 4に加えて、更新量が多く、回復処理の費用面の考慮がなされている。
  *   オペレータの介在が最少となるような高度に自動化された回復手順が必要である
  */
case class OnlineUpdate(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 複雑な処理
  * @param di
  * 0 要素のいずれにも該当しない
  * 1 要素のいずれか1項目に該当する
  * 2 要素のいずれか2項目に該当する
  * 3 要素のいずれか3項目に該当する
  * 4 要素のいずれか4項目に該当する
  * 5 要素の5項目すべてに該当する
  * <p>要素</p>
  * <ul>
  *   <li>きめ細かい処理または、セキュリティ処理</li>
  *   <li>広範な論理的処理</li>
  *   <li>広範な演算処理</li>
  *   <li>再処理が必要となる不完全処理への例外処理</li>
  *   <li>多様の入出力機能を扱う複雑な処理</li>
  * </ul>
  */
case class ComplexProcessing(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 再利用可能インストール
  * @param di
  * 0 再利用できるコードはない
  * 1 再利用できるコードがアプリケーション内で使用されている
  * 2 アプリケーションの10%未満て複数ユーザのニーズを考慮する
  * 3 アプリケーションの10%以上で複数ユーザのニーズを考慮する
  * 4 アプリケーションは再利用し易くするために特別なバッケージ化およぴ文書化がされている。
  *   またアプリケーションはソースコードレベルでユーザによりカスタマイズされる
  * 5 アプリケーションは再利用し易くするために特別なパッケージ化および文書化されていて、
  *   アプリケーションのカスタマイズはユーザパラメータの維持管理で可能てある
  */
case class ReUsability(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * インストール容易性
  * @param di
  * 0 導入に対してユーザの特別な指定はないまた特別な設置作業も不要てある
  * 1 導入に対してユーザからの特別な指定はないが、特別なセットアップが必要である
  * 2 移行および導入にユーザ指定があり、
  *   移行および導入ガイドを提供するとともにテストされているプロジェクトに対する移行の影響は重大ではない
  * 3 移行および導入にユーザ指定があり、
  *   移行および導入ガイドを提供するとともにテストされているプロジェクトに対する移行の影響は重大である
  * 4 2に加えて、自動移行および自動導入ツールを提供するとともにテストされている
  * 5 3に加えて、自動移行および自動導入ツールを提供するとともにテストされている
  */
case class InstallationEase(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 運用性
  * @param di
  * 0 通常のバックアップ手続き以外、ユーサからの運用上の指定は特にない
  * 1〜4 アプリケーションに適用される項目を以下から選択する。
  *      指定のあるものを除ぎ各項目の値を1度数としてカウントする。
  *        1) 効率的な始動、バックアップ、回復処理が提供されているがオぺレータの介入も必要である
  *        2) 効率的な始動、バックアップ、回復処理が提供されて村人オペレータの介入が必要な、 2度数としてカウントする。
  *        3) アプリケーションは、テープマウン卜の必要性が最小限になるようにしている
  *        4) アプリケーションは用紙操作の必要性が最小限になるようにしている
  * 5 アプリケーションは、無人操作となるように設計されている。
  *   無人操作とはアプリケーションの始動またはシャットダウン以外は、
  *   オぺレータがシステムを操作する必要がないことを意味する。
  *   自動エラー回復機能付きのアプリケーションである
  */
case class OperationalEase(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 複数サイト
  * @param di
  * 0 複数のサイトへの導入の要求はない
  * 1 複数サイトに対する必要性を考慮し、アプリケーションは、
  *   同一のハードウェア、ソフトウエア環境だけで運用するよう設計されている
  * 2 複数サイトこ対する必要性を考慮し、アプリケーションは、
  *   類似のハードウエア、ソフトウエア環境だけで運用するよう設計されている
  * 3 複数サイトに対する必要性を考慮し、アプリケーションは異なるハードウェア
  *   またはソフトウエア環境で運用できるよう設計されている
  * 4 複数サイトでアプリケーションを使用するためのドキュメントとサボー卜計画が提供され、テストされている。
  *   さらにアプリケーションの状態は1と2の記述の通りである
  * 5 複数サイトでアプリケーションを使用するためのドキュメントとサポート計画が提供され、テストされている。
  *   さらにアプリケーションの状態は3の記述の通りである
  */
case class MultipleSites(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}

/**
  * 変更容易性
  * @param di
  * 0 特性のいずれにも該当しない
  * 1 特性のいずれか1項目に該当する
  * 2 特性のいずれか2項目に該当する
  * 3 特性のいずれか3項目に該当する
  * 4 特性のいずれか4項目に該当する
  * 5 特性のいずれか5項目に該当する
  * <p>特性</p>
  * <ul>
  *   <li>1つのILFを対象とした簡単な要求を処理</li>
  *   <li>複数のILFを対象とした平均的な要求を処理</li>
  *   <li>複数のILFを対象とした複雑な要求を処理</li>
  *   <li>オンラインにてユーザが保守。翌営業日反映。</li>
  *   <li>オンラインにてユーザが保守。即時反映。</li>
  * </ul>
  */
case class FacilitateChange(di: DI) extends GeneralSystemCharacteristic {
  require(MIN_POINT <= di.value && di.value <= MAX_POINT)
}
