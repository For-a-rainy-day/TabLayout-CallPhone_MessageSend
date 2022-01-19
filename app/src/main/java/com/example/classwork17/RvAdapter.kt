package com.example.classwork17

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_nomer.view.*

class RvAdapter(var context: Context, var list: ArrayList<RvModel>) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(rvModel: RvModel, position: Int) {
            itemView.txt_name.text = rvModel.name
            itemView.txt_number.text = rvModel.number

            //object bilan xato ishladi
            // ozgaruvchi bilan vse ok
            var nam:String= itemView.txt_number.text.toString()

            MyData.number=itemView.txt_number.text.toString()
            MyData.name=itemView.txt_name.text.toString()

            itemView.img_pop.setOnClickListener {
                val popMenus=PopupMenu(context,it)
                popMenus.inflate(R.menu.pop_menu)
                popMenus.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.call_pop -> {

                            var REQUEST_PHONE_CALL= 1
                            //    var number=itemView.txt_number.text.toString()


                            if (context?.let { it1 -> ActivityCompat.checkSelfPermission(it1, Manifest.permission.CALL_PHONE) } != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
                            }

                            else {
                                var callIntent= Intent(Intent.ACTION_CALL)
                                //   callIntent.data= Uri.parse("tel:${ MyData.number}")
                                callIntent.data= Uri.parse("tel:${ nam.toString()}")
                                startActivity(context,callIntent, bundleOf())
                                Toast.makeText(context, "Calling", Toast.LENGTH_SHORT).show()
                            }

                            true
                        }
                        R.id.sms_pop->{
                            // var smsNumberUri= Uri.parse("sms:${ MyData.number}")
                            var smsNumberUri= Uri.parse("sms:${ nam.toString()}")
                            val smsIntent=Intent(Intent.ACTION_SENDTO, smsNumberUri)
                            startActivity(context,smsIntent,bundleOf())

                            Toast.makeText(context, "clickded sms", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else->true
                    }

                }
                popMenus.show()
            }

        }

        private fun call_pop(view:View) {
            val popMenus=PopupMenu(context,view)
            popMenus.inflate(R.menu.pop_menu)
            popMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.call_pop -> {

                        var REQUEST_PHONE_CALL= 1
                    //    var number=itemView.txt_number.text.toString()


                        if (context?.let { it1 -> ActivityCompat.checkSelfPermission(it1, Manifest.permission.CALL_PHONE) } != PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
               }

                else {
                    var callIntent= Intent(Intent.ACTION_CALL)
                 //   callIntent.data= Uri.parse("tel:${ MyData.number}")
                    callIntent.data= Uri.parse("tel:${ itemView.txt_number.toString()}")
                    startActivity(context,callIntent, bundleOf())
                    Toast.makeText(context, "Calling", Toast.LENGTH_SHORT).show()
                }

                        true
                    }
                R.id.sms_pop->{
                   // var smsNumberUri= Uri.parse("sms:${ MyData.number}")
                    var smsNumberUri= Uri.parse("sms:${ MyData.number}")
                    val smsIntent=Intent(Intent.ACTION_SENDTO, smsNumberUri)
                    startActivity(context,smsIntent,bundleOf())

                    Toast.makeText(context, "clickded sms", Toast.LENGTH_SHORT).show()
                    true
                }
                    else->true
                }

            }
            popMenus.show()
//            val popup=PopupMenu::class.java.getDeclaredField("img_pop")
//            popup.isAccessible=true
//            val menu= popup.get(popMenus)
//            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
//                    .invoke(menu, true)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_nomer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}