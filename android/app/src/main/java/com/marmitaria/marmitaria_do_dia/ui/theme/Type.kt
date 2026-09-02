package com.marmitaria.marmitaria_do_dia.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.R

val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val OswaldGoogleFont = GoogleFont("Oswald")
val DancingScriptGoogleFont = GoogleFont("Dancing Script")
val InterGoogleFont = GoogleFont("Inter")

val OswaldFamily = FontFamily(
    Font(googleFont = OswaldGoogleFont, fontProvider = fontProvider, weight = FontWeight.Normal),
    Font(googleFont = OswaldGoogleFont, fontProvider = fontProvider, weight = FontWeight.Medium),
    Font(googleFont = OswaldGoogleFont, fontProvider = fontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = OswaldGoogleFont, fontProvider = fontProvider, weight = FontWeight.Bold)
)

val DancingScriptFamily = FontFamily(
    Font(googleFont = DancingScriptGoogleFont, fontProvider = fontProvider, weight = FontWeight.Normal),
    Font(googleFont = DancingScriptGoogleFont, fontProvider = fontProvider, weight = FontWeight.Bold)
)

val InterFamily = FontFamily(
    Font(googleFont = InterGoogleFont, fontProvider = fontProvider, weight = FontWeight.Light),
    Font(googleFont = InterGoogleFont, fontProvider = fontProvider, weight = FontWeight.Normal),
    Font(googleFont = InterGoogleFont, fontProvider = fontProvider, weight = FontWeight.Medium),
    Font(googleFont = InterGoogleFont, fontProvider = fontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = InterGoogleFont, fontProvider = fontProvider, weight = FontWeight.Bold)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = OswaldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = 1.sp,
        color = TextWhite
    ),
    headlineMedium = TextStyle(
        fontFamily = OswaldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp,
        color = TextWhite
    ),
    titleLarge = TextStyle(
        fontFamily = OswaldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        lineHeight = 23.sp,
        color = TextWhite
    ),
    titleMedium = TextStyle(
        fontFamily = OswaldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = TextWhite
    ),
    bodyLarge = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = TextWhite
    ),
    bodyMedium = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        color = TextMuted
    ),
    labelSmall = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        color = TextMuted
    )
)
