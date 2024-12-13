package com.example.projectofmobile_group8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.BaseAdapter

class ProductAdapter(
    private val context: Context,
    private val productList: ArrayList<Product>,
    private val onQuantityChange: () -> Unit,
    private val showButtons: Boolean // Thêm tham số để kiểm soát hiển thị nút
) : BaseAdapter() {

    override fun getCount(): Int = productList.size

    override fun getItem(position: Int): Any = productList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.itemproduct, parent, false)

        val product = productList[position]
        val tvProductName = view.findViewById<TextView>(R.id.tv_product_name)
        val tvProductPrice = view.findViewById<TextView>(R.id.tv_product_price)
        val tvQuantity = view.findViewById<TextView>(R.id.tv_quantity)
        val btnIncrease = view.findViewById<Button>(R.id.btn_increase)
        val btnDecrease = view.findViewById<Button>(R.id.btn_decrease)
        val btnDelete = view.findViewById<ImageView>(R.id.btn_delete)

        // Gán dữ liệu cho sản phẩm
        tvProductName.text = product.name
        tvProductPrice.text = product.price
        tvQuantity.text = product.quantity.toString()

        // Hiển thị hoặc ẩn các nút dựa trên giá trị của `showButtons`
        if (showButtons) {
            btnIncrease.visibility = View.VISIBLE
            btnDecrease.visibility = View.VISIBLE
            btnDelete.visibility = View.VISIBLE

            // Tăng số lượng
            btnIncrease.setOnClickListener {
                product.quantity++
                tvQuantity.text = product.quantity.toString()
                onQuantityChange()
            }

            // Giảm số lượng
            btnDecrease.setOnClickListener {
                if (product.quantity > 1) {
                    product.quantity--
                    tvQuantity.text = product.quantity.toString()
                    onQuantityChange()
                }
            }

            // Xóa sản phẩm
            btnDelete.setOnClickListener {
                productList.removeAt(position)
                notifyDataSetChanged()
                onQuantityChange()
            }
        } else {
            // Ẩn các nút
            btnIncrease.visibility = View.GONE
            btnDecrease.visibility = View.GONE
            btnDelete.visibility = View.GONE
        }

        return view
    }
}
