import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofmobile_group8.Product
import com.example.projectofmobile_group8.R

class ProductAdapter(
    private val context: Context,
    private val productList: MutableList<Product>,
    private val onQuantityChange: () -> Unit, // Callback khi số lượng thay đổi
    private val showButtons: Boolean // Kiểm soát việc hiển thị các nút
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder để quản lý các view con trong RecyclerView
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        val tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
        val btnIncrease: Button = itemView.findViewById(R.id.btn_increase)
        val btnDecrease: Button = itemView.findViewById(R.id.btn_decrease)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)
    }

    // Tạo ViewHolder từ layout item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemproduct, parent, false)
        return ProductViewHolder(view)
    }

    // Gán dữ liệu cho ViewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Gán dữ liệu sản phẩm
        holder.tvProductName.text = product.name
        holder.tvProductPrice.text = "${product.priceInNumber} VND"
        holder.tvQuantity.text = product.quantity.toString()

        // Hiển thị hoặc ẩn các nút dựa trên giá trị của `showButtons`
        if (showButtons) {
            holder.btnIncrease.visibility = View.VISIBLE
            holder.btnDecrease.visibility = View.VISIBLE
            holder.btnDelete.visibility = View.VISIBLE

            // Tăng số lượng
            holder.btnIncrease.setOnClickListener {
                product.quantity++
                holder.tvQuantity.text = product.quantity.toString()
                onQuantityChange() // Gọi callback để cập nhật tổng tiền giỏ hàng
            }

            // Giảm số lượng
            holder.btnDecrease.setOnClickListener {
                if (product.quantity > 1) {
                    product.quantity--
                    holder.tvQuantity.text = product.quantity.toString()
                    onQuantityChange() // Gọi callback để cập nhật tổng tiền giỏ hàng
                }
            }

            // Xóa sản phẩm
            holder.btnDelete.setOnClickListener {
                productList.removeAt(position)
                notifyItemRemoved(position) // Cập nhật lại danh sách RecyclerView
                notifyItemRangeChanged(position, productList.size) // Sắp xếp lại các item
                onQuantityChange() // Gọi callback để cập nhật tổng tiền giỏ hàng
            }
        } else {
            // Ẩn các nút
            holder.btnIncrease.visibility = View.GONE
            holder.btnDecrease.visibility = View.GONE
            holder.btnDelete.visibility = View.GONE
        }
    }

    // Trả về số lượng sản phẩm
    override fun getItemCount(): Int {
        return productList.size
    }

    // Cập nhật danh sách sản phẩm khi có thay đổi
    fun updateProductList(newProductList: List<Product>) {
        productList.clear() // Xóa danh sách cũ
        productList.addAll(newProductList) // Thêm danh sách mới
        notifyDataSetChanged() // Thông báo RecyclerView cập nhật
    }
}
