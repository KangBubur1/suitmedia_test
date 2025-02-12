package suitmedia.mobile.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import suitmedia.mobile.core.domain.model.User
import suitmedia.mobile.databinding.ItemUserBinding

class UserAdapter(private val onItemClick: (User) -> Unit) : PagingDataAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK){
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      val data = getItem(position)
        if(data != null){
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: User) {
            Glide.with(itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivProfile)

            binding.tvName.text = data.first_name + " " + data.last_name
            binding.tvEmail.text = data.email
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}