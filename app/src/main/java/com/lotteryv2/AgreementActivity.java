package com.lotteryv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AgreementActivity extends AppCompatActivity {
    private TextView tv_agreement;
    private String agreement = "致会员：\n" +
            "1.当您在下注之后，请等待下注后的成功状态信息。\n" +
            "2.为了避免出现争议，您必须在下注之后检查「下注状况」。\n" +
            "3.任何的投诉必须在开奖之前提出，本公司不会受理任何开奖之后的投诉。\n" +
            "4.所有投注项目，公布赔率时出现的任何打字错误或非故意人为失误，本公司保留改正错误和按正确赔率结算投注的权力。\n" +
            "5.开奖后的投注，将被视为「无效」。所有赔率将不定时浮动，派彩时的赔率将以确认投注时之赔率为准。\n" +
            "6.敬告有意与本公司博彩之客户，应注意您所在的国家或居住地可能规定网络博彩不合法，若此情况属实，本公司将不接受任何客户因违反当地博彩或赌博法令所引起之任何责任。\n" +
            "7.倘若发生遭黑客入侵破坏行为或不可抗拒之灾害导致系统故障或资料损坏，资料丢失等情况，我们将以本公司线上交易之后备资料为最后处理依据；为确保各方真实利益，请各会员交易后打印资料，本公司才接受投诉及处理。\n" +
            "8.为避免纠纷，各会员在交易之后，务必进入下注明细检查，若发生任何异常，请立即与代理商联系查证，否则交易会员必须同意，一切交易历史将以本公司资料库中资料为准，不得异议。\n" +
            "9.如本公司机房遇天灾，停电或不可抗力之因素,导致无法运作时，得中止所有未开奖前之投注，在本公司中止下注前,会员所有投注仍属有效，不得要求取消或延迟交收,以及不得有任何异议。\n" +
            "10.如发生临时性、突发性等特殊情况，本公司有权作出相对应之决定，不得异议。 \n" +
            "11.本公司所有投注皆含本金，请认真了解规则说明。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);

        tv_agreement = (TextView) findViewById(R.id.tv_agreement);
        tv_agreement.setText(agreement);
        tv_agreement.setTextSize(16);
    }
}
