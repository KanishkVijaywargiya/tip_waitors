package androwebiosz.epizy.com.tippy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.amount_text_view)
    EditText AmountTextView;

    @BindView(R.id.tip_percent_text_view)
    TextView TipPercentTextView;

    @BindView(R.id.tip_Amt_text_view)
    TextView TipAmountTextView;

    @BindView(R.id.bill_total_text_view)
    TextView BillTotalTextView;

    float percentage = 0;
    float tipTotalAmount = 0;
    float billAmountTotal = 0;
    float initialBillAmount = 0;

    float DefaultTipPercent = 0;
    float tipPercent1 = 3;
    float tipPercent2 = 5;
    float tipPercent3 = 7;
    float tipPercent4 = 10;
    float tipPercent5 = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(savedInstanceState != null){
            percentage = savedInstanceState.getFloat("percent");
            calculateFinalView();
        }
        setTipValues();
    }

    private void setTipValues(){
        TipPercentTextView.setText(getString(R.string.tip_percent, percentage));
        TipAmountTextView.setText(getString(R.string.tip_amt, tipTotalAmount));
        BillTotalTextView.setText(getString(R.string.rupees, billAmountTotal));
    }

    private void calculateFinalView() {
        if(AmountTextView.getText().toString().equals("") || AmountTextView.getText().toString().equals(".")){
            initialBillAmount = 0;
        }else{
            initialBillAmount = Float.valueOf(AmountTextView.getText().toString());
        }
        tipTotalAmount = (initialBillAmount * percentage) / 100;
        billAmountTotal = initialBillAmount + tipTotalAmount;
    }

    @OnClick({R.id.serviceRateIcon1, R.id.serviceRateIcon2, R.id.serviceRateIcon3, R.id.serviceRateIcon4, R.id.serviceRateIcon5, R.id.serviceRateIcon6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.serviceRateIcon1:
                percentage = DefaultTipPercent;
                break;
            case R.id.serviceRateIcon2:
                percentage = tipPercent1;
                break;
            case R.id.serviceRateIcon3:
                percentage = tipPercent2;
                break;
            case R.id.serviceRateIcon4:
                percentage = tipPercent3;
                break;
            case R.id.serviceRateIcon5:
                percentage = tipPercent4;
                break;
            case R.id.serviceRateIcon6:
                percentage = tipPercent5;
                break;
        }
        calculateFinalView();
        setTipValues();
    }

    @OnTextChanged(R.id.amount_text_view)
    public void onTextChanged(){
        calculateFinalView();
        setTipValues();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putFloat("percent", percentage);
        super.onSaveInstanceState(outState);
    }
}
