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

class ProductRecyclerViewAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val isForPayment: Boolean // Quyết định sử dụng layout nào
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductName: TextView = view.findViewById(R.id.tv_product_name)
        val tvProductPrice: TextView = view.findViewById(R.id.tv_product_price)
        val tvQuantity: TextView = view.findViewById(R.id.tv_quantity)
        val btnIncrease: Button? = view.findViewById(R.id.btn_increase)
        val btnDecrease: Button? = view.findViewById(R.id.btn_decrease)
        val btnDelete: ImageView? = view.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = if (isForPayment) {
            R.layout.item_product_payment // Layout cho màn hình thanh toán
        } else {
            R.layout.itemproduct // Layout cho màn hình giỏ hàng
        }
        val view = LayoutInflater.from(context).inflate(layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Gán dữ liệu cho View
        holder.tvProductName.text = product.name
        holder.tvProductPrice.text = product.price
        holder.tvQuantity.text = "Số lượng: ${product.quantity}"

        if (!isForPayment) {
            // Hiển thị và xử lý logic nút thêm/bớt
            holder.btnIncrease?.visibility = View.VISIBLE
            holder.btnDecrease?.visibility = View.VISIBLE
            holder.btnDelete?.visibility = View.VISIBLE

            holder.btnIncrease?.setOnClickListener {
                product.quantity++
                notifyItemChanged(position)
            }

            holder.btnDecrease?.setOnClickListener {
                if (product.quantity > 1) {
                    product.quantity--
                    notifyItemChanged(position)
                }
            }

            holder.btnDelete?.setOnClickListener {
                (productList as ArrayList).removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, productList.size)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}
