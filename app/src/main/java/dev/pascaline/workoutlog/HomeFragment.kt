package dev.pascaline.workoutlog

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import dev.pascaline.workoutlog.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    var videoOne: VideoView? = null
    var videoTwo:VideoView?=null
    var videoThree:VideoView?=null
    var videoFour:VideoView?=null

    var mediaControls: MediaController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
//https://www.geeksforgeeks.org/videoview-in-kotlin/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayOne(view)
        displayTwo(view)
        displayThree(view)
        displayFour(view)

//        simpleVideoView!!.setVideoURI(Uri.parse("android.resource://R.raw.one"))
    }
    fun displayOne(view:View){
        videoOne = view.findViewById(R.id.vdOne) as VideoView

        if (mediaControls == null) {
            mediaControls = MediaController(context)
            mediaControls!!.setAnchorView(this.videoOne)
        }
        videoOne!!.setMediaController(mediaControls)
        videoOne!!.setVideoURI(Uri.parse("android.resource://"
                +  requireActivity().packageName + "/" + R.raw.one))
        videoOne!!.requestFocus()
        videoOne!!.start()
    }
    fun displayTwo(view:View){
        videoTwo = view.findViewById(R.id.vdTwo) as VideoView

        if (mediaControls == null) {
            mediaControls = MediaController(context)
            mediaControls!!.setAnchorView(this.videoTwo)
        }
        videoTwo!!.setMediaController(mediaControls)
        videoTwo!!.setVideoURI(Uri.parse("android.resource://"
                +  requireActivity().packageName + "/" + R.raw.one))
        videoTwo!!.requestFocus()
        videoTwo!!.start()
    }
    fun displayThree(view:View){
        videoThree = view.findViewById(R.id.vdThree) as VideoView

        if (mediaControls == null) {
            mediaControls = MediaController(context)
            mediaControls!!.setAnchorView(this.videoThree)
        }
        videoThree!!.setMediaController(mediaControls)
        videoThree!!.setVideoURI(Uri.parse("android.resource://"
                +  requireActivity().packageName + "/" + R.raw.one))
        videoThree!!.requestFocus()
        videoThree!!.start()
    }
    fun displayFour(view:View){
        videoFour = view.findViewById(R.id.vdFour) as VideoView

        if (mediaControls == null) {
            mediaControls = MediaController(context)
            mediaControls!!.setAnchorView(this.videoFour)
        }
        videoFour!!.setMediaController(mediaControls)
        videoFour!!.setVideoURI(Uri.parse("android.resource://"
                +  requireActivity().packageName + "/" + R.raw.one))
        videoFour!!.requestFocus()
        videoFour!!.start()
    }
}