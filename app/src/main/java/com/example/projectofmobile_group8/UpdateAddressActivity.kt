package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class UpdateAddressActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etTinh: EditText
    private lateinit var etHuyen: EditText
    private lateinit var etXa: EditText
    private lateinit var rbNhaRieng: RadioButton
    private lateinit var rbCongTy: RadioButton
    private lateinit var btnBacktobuy_items: ImageView
    private lateinit var savebutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_address)
        setControl()
        setEvent()
    }

    private fun setControl() {
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etTinh = findViewById(R.id.etTinh)
        etHuyen = findViewById(R.id.etHuyen)
        etXa = findViewById(R.id.etXa)
        rbNhaRieng = findViewById(R.id.rbNhaRieng)
        rbCongTy = findViewById(R.id.rbCongTy)
        btnBacktobuy_items = findViewById(R.id.btnBacktobuy_items)
        savebutton = findViewById(R.id.saveButton)

        // Nhận dữ liệu từ Intent
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val tinh = intent.getStringExtra("province")
        val huyen = intent.getStringExtra("district")
        val xa = intent.getStringExtra("ward")
        val addressType = intent.getStringExtra("addressType")

        // Điền thông tin
        etName.setText(name)
        etPhone.setText(phone)
        etTinh.setText(tinh)
        etHuyen.setText(huyen)
        etXa.setText(xa)

        // Chọn đúng RadioButton
        if (addressType == "Nhà riêng") {
            rbNhaRieng.isChecked = true
            rbCongTy.isChecked = false
        } else {
            rbCongTy.isChecked = true
            rbNhaRieng.isChecked = false
        }
    }

    private fun setEvent() {
        savebutton.setOnClickListener {
            val updatedName = etName.text.toString()
            val updatedPhone = etPhone.text.toString()
            val updatedProvince = etTinh.text.toString()
            val updatedDistrict = etHuyen.text.toString()
            val updatedWard = etXa.text.toString()
            val updatedAddressType = if (rbNhaRieng.isChecked) "Nhà riêng" else "Công ty"

            // Trả kết quả về
            val resultIntent = Intent()
            resultIntent.putExtra("name", updatedName)
            resultIntent.putExtra("phone", updatedPhone)
            resultIntent.putExtra("province", updatedProvince)
            resultIntent.putExtra("district", updatedDistrict)
            resultIntent.putExtra("ward", updatedWard)
            resultIntent.putExtra("addressType", updatedAddressType)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        btnBacktobuy_items.setOnClickListener {
            setResult(RESULT_CANCELED) // Không trả dữ liệu mới
            finish()
        }
    }
}
