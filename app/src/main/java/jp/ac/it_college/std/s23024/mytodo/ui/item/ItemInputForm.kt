package jp.ac.it_college.std.s23024.mytodo.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s23024.mytodo.R

@Composable
fun ItemInputForm(
    modifier: Modifier = Modifier,
    itemDetails: ItemDetails,
    onValueChange: (ItemDetails) -> Unit = {}
) {
   Column(
       modifier = modifier,
       verticalArrangement = Arrangement.spacedBy(
           dimensionResource(id = R.dimen.padding_medium)
       )
   ) {
       OutlinedTextField(
           value = itemDetails.title,
           onValueChange = { onValueChange(itemDetails.copy(title = it)) },
           label = { Text("Todo名称*") },
           modifier = Modifier.fillMaxWidth(),
           singleLine = true
       )
       OutlinedTextField(
           value = itemDetails.description,
           onValueChange = {
               onValueChange(itemDetails.copy(description = it))
           },
           label = { Text("Todo詳細*") },
           modifier = Modifier.fillMaxWidth(),
           singleLine = true
       )
       Row(
           Modifier
               .fillMaxWidth()
               .height(56.dp)
               .toggleable(
                   value = itemDetails.done,
                   onValueChange = { onValueChange(itemDetails.copy(done = it)) },
                   role = Role.Checkbox
               )
               .padding(horizontal = 16.dp),
           verticalAlignment = Alignment.CenterVertically
       ) {
           Checkbox(checked = itemDetails.done, onCheckedChange = null)
           Text(
               text = "完了",
               style = MaterialTheme.typography.bodyLarge,
               modifier = Modifier.padding(start = 16.dp)
           )
       }
       Text(
           modifier = Modifier.padding(
               start = dimensionResource(id = R.dimen.padding_medium)
           ),
           text = "*は必須入力です"
       )
   }
}

@Preview(showBackground = true)
@Composable
private fun ItemInputFormPreview() {
    ItemInputForm(itemDetails = ItemDetails())
}