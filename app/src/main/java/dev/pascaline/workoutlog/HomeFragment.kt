package dev.pascaline.workoutlog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import dev.pascaline.workoutlog.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    var simpleVideoView: VideoView? = null
    var mediaControls: MediaController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        simpleVideoView = VideoView(context).findViewById(R.id.vdOne) as VideoView

        if (mediaControls == null) {
            mediaControls = MediaController(context)
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }
    }


}