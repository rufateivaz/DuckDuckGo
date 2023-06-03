import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.domain.characters.model.Character
import com.sample.viewer.R

class CharacterAdapter(
    private val onClick: (character: Character) -> Unit
) : ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffUtil) {

    companion object CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem.title == newItem.title
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.character_item, viewGroup, false)

        return ViewHolder(view) {
            onClick(currentList[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    class ViewHolder(
        itemView: View,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView

        init {
            title = itemView.findViewById(R.id.title)
            itemView.setOnClickListener { onItemClicked(bindingAdapterPosition) }
        }

        fun bind(character: Character) {
            title.text = character.title
        }
    }

    fun saveData(characters: List<Character>) {
        submitList(characters)
    }
}
