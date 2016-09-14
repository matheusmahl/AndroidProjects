package br.edu.pdm.multiconversor;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConversorActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtValor;
    private EditText edtConversao;
    private RadioGroup rgMoedas;
    private Button btnCalcular;
    private Button btnSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        edtValor = (EditText)findViewById(R.id.edtValor);
        edtConversao = (EditText)findViewById(R.id.edtConversao);
        rgMoedas = (RadioGroup)findViewById(R.id.rgMoedas);

        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        btnSobre = (Button) findViewById(R.id.btnSobre);
        btnSobre.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCalcular:
                double valor;
                double valorConversao;
                try {
                    valor = Double.parseDouble(edtValor.getText().toString().trim());
                    valorConversao = Double.parseDouble(edtConversao.getText().toString().trim());
                }catch (Exception e){
                    Toast.makeText(ConversorActivity.this, "Valor inválido", Toast.LENGTH_SHORT).show();
                    break;
                }

                double valorConvertido = Calcular(valor,valorConversao);
                DecimalFormat precision = new DecimalFormat("0.00");

                switch (rgMoedas.getCheckedRadioButtonId()){

                    case R.id.rbDolar:
                        Toast.makeText(ConversorActivity.this, "Resultado: " + precision.format(valorConvertido) + " dólares.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbEuro:
                        Toast.makeText(ConversorActivity.this, "Resultado: " + precision.format(valorConvertido) + " euros.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbPeso:
                        Toast.makeText(ConversorActivity.this, "Resultado: " + precision.format(valorConvertido) + " pesos.", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(ConversorActivity.this, "Selecione uma opção de moeda.", Toast.LENGTH_SHORT).show();
                }

                //limparCampos(); //Limpar ou manter, eis a questao.
                break;

            case R.id.btnSobre:
            /* Intent it = new Intent(ConversorActivity.this, SobreActivity.class);
                startActivity(it);
*/
                startActivity(new Intent(getBaseContext(), SobreActivity.class));
                break;

        }

    }

    private double Calcular(double valor, double valorConversao) {
        return valor / valorConversao;

    }

    private void limparCampos() {
        edtValor.setText("");
        edtConversao.setText("");
        edtValor.requestFocus();
    }
}
