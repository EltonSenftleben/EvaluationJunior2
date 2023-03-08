package com.example.evaluationjunior_2.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import java.io.ByteArrayOutputStream

object Convert {

   fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, stream)
        return stream.toByteArray()
    }

    @SuppressLint("RememberReturnType")
    @Composable
     fun BytearrayToPainter(byteArray: ByteArray): Painter {
        return remember(byteArray) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            BitmapPainter(bitmap.asImageBitmap())
        }
    }
}