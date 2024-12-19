package com.example.projectofmobile_group8

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// Adapter để hiển thị danh sách sản phẩm trong RecyclerView.
// Xử lý việc tạo View, gắn dữ liệu vào View và các sự kiện click.

class HomeProductAdapter(private val productList: List<HomeProduct>) : RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder>() {


    // Được gọi khi cần tạo mới một ViewHolder. Chịu trách nhiệm inflate layout cho item.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = inflateView(parent) // Gọi hàm inflateView để tạo View.
        return ProductViewHolder(view) // Trả về ViewHolder mới được tạo.
    }


    // Được gọi để gắn dữ liệu vào một ViewHolder đã tồn tại, dựa trên vị trí của item.

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position] // Lấy sản phẩm tại vị trí hiện tại.
        holder.bind(product) // Gọi hàm bind để gắn dữ liệu vào ViewHolder.
    }


    // Trả về tổng số lượng item trong danh sách.

    override fun getItemCount(): Int = productList.size


    // Inflate layout của một item.

    private fun inflateView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
    }


    // Lớp ViewHolder để quản lý các View của một item sản phẩm.

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.productImage) // Ảnh sản phẩm.
        private val productName: TextView = itemView.findViewById(R.id.productName) // Tên sản phẩm.
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice) // Giá sản phẩm.

        // Gắn dữ liệu của sản phẩm vào các View trong ViewHolder.

        fun bind(product: HomeProduct) {
            displayProductDetails(product) // Hiển thị chi tiết sản phẩm lên View.
            setItemClickListener(product) // Đặt sự kiện click cho item.
        }


        // Hiển thị chi tiết sản phẩm lên các View tương ứng.

        private fun displayProductDetails(product: HomeProduct) {
            productImage.setImageResource(product.imageResource) // Hiển thị ảnh sản phẩm.
            productName.text = product.name // Hiển thị tên sản phẩm.
            productPrice.text = "$${product.price}" // Hiển thị giá sản phẩm.
        }


        // Đặt sự kiện click cho item, khi click sẽ mở màn hình chi tiết sản phẩm.

        private fun setItemClickListener(product: HomeProduct) {
            itemView.setOnClickListener {
                navigateToProductDetail(product) // Điều hướng sang màn hình chi tiết sản phẩm.
            }
        }

        // Điều hướng sang màn hình chi tiết sản phẩm.

        private fun navigateToProductDetail(product: HomeProduct) {
            val intent = Intent(itemView.context, ProductDetailActivity::class.java).apply {
                // Truyền dữ liệu của sản phẩm qua Intent.
                putExtra("productName", product.name) // Truyền tên sản phẩm.
                putExtra("productPrice", product.price) // Truyền giá sản phẩm.
                putExtra("productDescription", product.description) // Truyền mô tả sản phẩm.
                putExtra("productImage", product.imageResource) // Truyền ảnh sản phẩm.

                // Truyền danh sách ảnh nhỏ nếu có.
                putIntegerArrayListExtra("smallImages", ArrayList(product.smallImages))
            }
            itemView.context.startActivity(intent) // Bắt đầu màn hình chi tiết sản phẩm.
        }
    }
}
