package negara.asean.aseancountry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import negara.asean.aseancountry.model.CountriesData
import negara.asean.aseancountry.model.Country

@Composable
fun Home(
    modifier: Modifier,
//    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
){
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        val _sortedCountry = MutableStateFlow(
            CountriesData.countries
                .sortedBy { it.name }
        )
        val sortedCountry: MutableStateFlow<List<Country>> = _sortedCountry

//        val sortedCountry by viewModel.sortedCountry.collectAsState()
//        val query by viewModel.query

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp),
        ) {
//            item {
//                SearchBar(
//                    query = query,
//                    onQueryChange = viewModel::search,
//                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
//                )
//            }

//            items(sortedCountry) { sort ->
//                CountryItem(country = sort)
//            }
            items(CountriesData.countries, key = { it.id }) { hero ->
                CountryItem(
                    country = hero
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_hero))
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

@Composable
fun CountryItem(country: Country) {
    Row(
        modifier = Modifier
            .border(1.dp, Color.Black, shape = RectangleShape)
            .clickable {},
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = country.photo),
            contentDescription = "Flag of ${country.name}",
            modifier = Modifier
                .size(80.dp)
                .border(1.dp, Color.Black, shape = RectangleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = country.name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}