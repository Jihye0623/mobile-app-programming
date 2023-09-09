import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.my11application.R
import com.example.my11application.databinding.ItemRecyclerviewBinding

// 리사이클러 뷰 - 뷰홀더
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

// 리사이클러 뷰 - 어댑터
class MyAdapter(val datas:MutableList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas.size
    }   // 관리하는 데이터 개수

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }   // 뷰홀더 리턴 (마이 어댑터 - 뷰홀더 연결)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas[position]
    }   // 선택된 곳에 뷰 홀더 리턴
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // 배경 이미지 출력
        super.onDrawOver(c, parent, state)
        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)

        val drW = dr?.intrinsicWidth
        val drH = dr?.intrinsicHeight
        val left = parent.width/2 - drW?.div(2) as Int
        val top = parent.height/2 - drH?.div(2) as Int
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), left.toFloat(), top.toFloat(), null)

    }
}

// New - Kotiln Class