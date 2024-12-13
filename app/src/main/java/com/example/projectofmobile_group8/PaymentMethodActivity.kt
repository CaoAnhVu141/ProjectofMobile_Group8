package com.example.projectofmobile_group8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class PaymentMethodActivity : AppCompatActivity() {

    private lateinit var radioGroupPaymentMethods: RadioGroup
    private lateinit var rbCash: RadioButton
    private lateinit var rbZaloPay: RadioButton
    private lateinit var rbMoMo: RadioButton
    private lateinit var rbApple: RadioButton
    private lateinit var rbATM: RadioButton
    private lateinit var btnXacnhan: Button

    private lateinit var llCash: LinearLayout
    private lateinit var llZaloPay: LinearLayout
    private lateinit var llMoMo: LinearLayout
    private lateinit var llApple: LinearLayout
    private lateinit var llATM: LinearLayout

    private lateinit var btnReturn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)
        setControl()
        setEvent()
    }

    private fun setControl() {
        radioGroupPaymentMethods = findViewById(R.id.radioGroupPaymentMethods)
        rbCash = findViewById(R.id.rbCash)
        rbZaloPay = findViewById(R.id.rbZaloPay)
        rbMoMo = findViewById(R.id.rbMoMo)
        rbApple = findViewById(R.id.rbApple)
        rbATM = findViewById(R.id.rbATM)
        btnXacnhan = findViewById(R.id.btnXacnhan)

        llCash = findViewById(R.id.llCash)
        llZaloPay = findViewById(R.id.llZaloPay)
        llMoMo = findViewById(R.id.llMoMo)
        llApple = findViewById(R.id.llApple)
        llATM = findViewById(R.id.llATM)

        btnReturn = findViewById(R.id.ivBacktoPayment)

        val currentPaymentMethod = intent.getStringExtra("paymentMethod") ?: "Tiền mặt"
        setSelectedPaymentMethod(currentPaymentMethod)
    }

    private fun setEvent() {
        llCash.setOnClickListener { selectPaymentMethod(R.id.rbCash) }
        llZaloPay.setOnClickListener { selectPaymentMethod(R.id.rbZaloPay) }
        llMoMo.setOnClickListener { selectPaymentMethod(R.id.rbMoMo) }
        llApple.setOnClickListener { selectPaymentMethod(R.id.rbApple) }
        llATM.setOnClickListener { selectPaymentMethod(R.id.rbATM) }

        rbCash.setOnClickListener { selectPaymentMethod(R.id.rbCash) }
        rbZaloPay.setOnClickListener { selectPaymentMethod(R.id.rbZaloPay) }
        rbMoMo.setOnClickListener { selectPaymentMethod(R.id.rbMoMo) }
        rbApple.setOnClickListener { selectPaymentMethod(R.id.rbApple) }
        rbATM.setOnClickListener { selectPaymentMethod(R.id.rbATM) }

        btnXacnhan.setOnClickListener {
            val selectedMethod = when (radioGroupPaymentMethods.checkedRadioButtonId) {
                R.id.rbCash -> "Tiền mặt"
                R.id.rbZaloPay -> "Zalo Pay"
                R.id.rbMoMo -> "MoMo"
                R.id.rbApple -> "Apple Pay"
                R.id.rbATM -> "Thẻ ATM"
                else -> "Tiền mặt"
            }
            val resultIntent = Intent()
            resultIntent.putExtra("paymentMethod", selectedMethod)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        btnReturn.setOnClickListener {
            finish()
        }
    }

    private fun setSelectedPaymentMethod(method: String) {
        when (method) {
            "Tiền mặt" -> radioGroupPaymentMethods.check(R.id.rbCash)
            "Zalo Pay" -> radioGroupPaymentMethods.check(R.id.rbZaloPay)
            "MoMo" -> radioGroupPaymentMethods.check(R.id.rbMoMo)
            "Apple Pay" -> radioGroupPaymentMethods.check(R.id.rbApple)
            "Thẻ ATM" -> radioGroupPaymentMethods.check(R.id.rbATM)
        }
    }

    private fun selectPaymentMethod(radioButtonId: Int) {
        radioGroupPaymentMethods.check(radioButtonId)
    }
}
