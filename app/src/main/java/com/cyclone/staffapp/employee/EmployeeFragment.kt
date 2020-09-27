package com.cyclone.staffapp.employee

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.cyclone.staffapp.R
import com.cyclone.staffapp.db.DataBase
import com.cyclone.staffapp.db.EmployeeDB
import com.cyclone.staffapp.db.SpecialtyDB
import com.cyclone.staffapp.setImage
import kotlinx.android.synthetic.main.employee_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class EmployeeFragment : Fragment(R.layout.employee_fragment) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getLong("id")

        lateinit var employee: EmployeeDB
        lateinit var specialtyDB: List<SpecialtyDB>

        Handler {

            val executor = Executors.newSingleThreadExecutor()

            executor.submit {
                employee = DataBase.getDataBase(context!!).employeeDAO().getById(id)
                specialtyDB = DataBase.getDataBase(context!!).specialtyDAO().getAll()
            }

            try {
                executor.shutdown()
                executor.awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                if (!executor.isShutdown) executor.shutdownNow()

                name.text = "${employee.firstName} ${employee.lastName}"

                birthday.text = if (employee.birthday == null) "-"
                else SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(employee.birthday!!)

                specialty.text =
                    specialtyDB.filter { specialtyDB -> employee.specialty.contains(specialtyDB) }
                        .joinToString(",")
                avatar.setImage(employee.avatarURI)
            }

            true
        }.sendEmptyMessage(0)
    }

}