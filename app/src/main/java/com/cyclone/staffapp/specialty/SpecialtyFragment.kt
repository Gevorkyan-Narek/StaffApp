package com.cyclone.staffapp.specialty

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.cyclone.staffapp.R
import com.cyclone.staffapp.db.DataBase
import com.cyclone.staffapp.db.SpecialtyDB
import kotlinx.android.synthetic.main.speciality_fragment.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SpecialtyFragment : Fragment(R.layout.speciality_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var specialtyList: List<SpecialtyDB>

        Handler {

            val executor = Executors.newSingleThreadExecutor()

            executor.submit {
                specialtyList = DataBase.getDataBase(context!!).specialtyDAO().getAll()
            }

            try {
                executor.shutdown()
                executor.awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                if (!executor.isShutdown) executor.shutdownNow()
                specialtyRecycler.adapter = SpecialtyAdapter(specialtyList)
            }

            true
        }.sendEmptyMessage(0)

    }
}