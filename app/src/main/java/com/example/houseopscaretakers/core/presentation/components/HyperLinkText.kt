package com.example.houseopscaretakers.core.presentation.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.navigation.Direction
import com.example.houseopscaretakers.navigation.Screen

@Composable
fun HyperLinkText(
    modifier: Modifier = Modifier,
    direction: Direction,
    fullText: String,
    fullTextColor: Color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
    linkText: List<String>,
    linkTextColor: Color = MaterialTheme.colorScheme.primary,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    hyperlinks: List<String>,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize
) {

    val annotatedString = buildAnnotatedString {
        //  add the full text
        append(fullText)

        //  for every link text item
        linkText.forEachIndexed { index, link ->

            //  start & end index
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length

            //  add style to that hyperlink
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    textDecoration = linkTextDecoration,
                    fontWeight = linkTextFontWeight
                ),
                start = startIndex,
                end = endIndex
            )

            //  add annotation
            addStringAnnotation(
                tag = com.example.houseopscaretakers.core.Constants.ROUTE,
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        //  style for the whole text
        addStyle(
            style = SpanStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            ),
            start = 0,
            end = fullText.length
        )
    }

    //  clickable text
    ClickableText(
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations(com.example.houseopscaretakers.core.Constants.ROUTE, it, it)
                .firstOrNull()?.let { stringAnnotation ->

                    //  navigate to the current route
                    direction.navigateToRoute(stringAnnotation.item)
                }
        }
    )
}


































