package dev.mcd.redditsortof.presentation.listing

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dev.mcd.redditsortof.R
import dev.mcd.redditsortof.databinding.LinkItemBinding
import dev.mcd.redditsortof.domain.listing.Link

class LinkItem(private val link: Link) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(LinkItemBinding.bind(viewHolder.itemView)) {
            title.text = link.title
            score.text = link.score.toString()
            author.text = link.author
        }
    }

    override fun getLayout(): Int {
        return R.layout.link_item
    }
}
