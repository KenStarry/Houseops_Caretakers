package com.example.houseopscaretakers.core.presentation.components

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
import com.example.houseopscaretakers.core.Constants

@Composable
fun HyperLinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = MaterialTheme.colorScheme.primary,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    hyperlinks: List<String>,
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize
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
                tag = Constants.ROUTE,
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        //  style for the whole text
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
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
                .getStringAnnotations(Constants.ROUTE, it, it)
                .firstOrNull()?.let { route ->
                    //  navigate to page with that route

                }
        }
    )
}


































