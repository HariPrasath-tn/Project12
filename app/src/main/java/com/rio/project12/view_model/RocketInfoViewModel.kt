package com.rio.project12.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rio.project12.model.Rec
import okhttp3.HttpUrl

class RocketInfoViewModel(var application: Application) :ViewModel(){
    private val _id = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()
    private val _details = MutableLiveData<String>()
    private val _isSuccess = MutableLiveData<String>()
    private val _date = MutableLiveData<String>()
    private val _wiki = MutableLiveData<String>()
    private val _article = MutableLiveData<String>()
    private val _youTubeId = MutableLiveData<String>()
    private val _image = MutableLiveData<String>()

    val id:LiveData<String>
        get() = _id
    val name:LiveData<String>
        get() = _name
    val details:LiveData<String>
        get() = _details
    val date:LiveData<String>
        get() = _date
    val isSuccess:LiveData<String>
        get() = _isSuccess
    val wiki:LiveData<String>
        get() = _wiki
    val article:LiveData<String>
        get() = _article
    val youTubeID:LiveData<String>
        get() = _youTubeId
    val image:LiveData<String>
        get() = _image

    init{
        _image.value = Rec.dRocketHistory.image ?: Rec.noImageFound
        _id.value = Rec.dRocketHistory.id ?: "Not Available"
        _name.value = Rec.dRocketHistory.name ?: "Not Available"
        _date.value = Rec.dRocketHistory.date_utc ?: "Not Available"
        _details.value = Rec.dRocketHistory.details ?: "Not Available"
        _isSuccess.value = Rec.dRocketHistory.success.toString() ?: "Not Available"
        _wiki.value = Rec.dRocketHistory.wiki ?: "Not Available"
        _article.value = Rec.dRocketHistory.article ?: "not Available"
        _youTubeId.value = Rec.dRocketHistory.youtube_id ?: "Not Available"
    }
}