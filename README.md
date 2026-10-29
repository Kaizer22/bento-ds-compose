# Bento DS Compose

Jetpack Compose implementation of the open-source [Bento Design System](https://www.bento-ds.com/).
The components follow the Bento DS look and props where Android allows it; every component's KDoc
links to its Bento DS page and lists what differs.

Requirements: `minSdk` 28, Compose BOM 2025.06 or newer, Material 3.

## Theme

Wrap the content in `BentoDSTheme`. Components read colors, typography, spacing, shapes and motion
only from the theme, so a custom palette restyles the whole library.

```kotlin
BentoDSTheme(
    lightColors = paLightColors(),
    darkColors = paDarkColors(),
) {
    BentoDSButton(text = "Save", onClick = { /* ... */ })
}
```

`BentoDSTheme` also maps the Bento roles onto a Material 3 color scheme, so stock Material components
inside the tree use Bento colors.

## Components

"Extension" means the component has no counterpart in Bento DS.

| Group | Component | Bento DS |
|---|---|---|
| Buttons | `BentoDSButton` | [Button](https://developer.bento-ds.com/Components/Button) |
| | `BentoDSIconButton` | [Button](https://developer.bento-ds.com/Components/Button), icon only |
| | `Link` | [Typography](https://developer.bento-ds.com/Guides/typography), link styles |
| Info | `Action` | [Actions](https://developer.bento-ds.com/Components/Actions) |
| | `Avatar` | [Avatar](https://developer.bento-ds.com/Components/Avatar) |
| | `BentoDSBanner` | [Banner](https://developer.bento-ds.com/Components/Banner) |
| | `BentoDSCard` | [Card](https://developer.bento-ds.com/Components/Card) |
| | `Chip` | [Chip](https://developer.bento-ds.com/Components/Chip) |
| | `BentoDSDivider` | [Divider](https://developer.bento-ds.com/Components/Divider) |
| | `BentoDSFeedback` | [Feedback](https://developer.bento-ds.com/Components/Feedback) |
| | `BentoDSIcon` | [Icons](https://developer.bento-ds.com/Guides/icons) |
| | `BentoDSListItem`, `BentoDSListHeader` | [List](https://developer.bento-ds.com/Components/List) |
| | `Menu` | [Menu](https://developer.bento-ds.com/Components/Menu) |
| | `BentoDSMonthCalendar` | Extension |
| Input | `BentoDSCheckbox` | [Checkbox](https://developer.bento-ds.com/Components/Checkbox), [CheckboxField](https://developer.bento-ds.com/Components/CheckboxField) |
| | `BentoDSDateField` | [DateField](https://developer.bento-ds.com/Components/DateField) |
| | `BentoDSDatepicker` | [DateField](https://developer.bento-ds.com/Components/DateField) picker |
| | `DatetimePicker` | Extension |
| | `DropdownSelector` | Extension |
| | `FileUploader` | [FileUploaderField](https://developer.bento-ds.com/Components/FileUploaderField) |
| | `BentoDSNumberStepper` | Extension |
| | `BentoDSRadio` | [RadioGroupField](https://developer.bento-ds.com/Components/RadioGroupField) |
| | `BentoDSSearchBar` | [SearchBar](https://developer.bento-ds.com/Components/SearchBar) |
| | `BentoDSSegmentedControl` | Extension |
| | `BentoDSSelectField` | [SelectField](https://developer.bento-ds.com/Components/SelectField) |
| | `BentoDSSwitch` | [Switch](https://developer.bento-ds.com/Components/Switch) |
| | `BentoDSTextField` | [TextField](https://developer.bento-ds.com/Components/TextField), [TextArea](https://developer.bento-ds.com/Components/TextArea) |
| | `BentoDSTimeField` | [TimeField](https://developer.bento-ds.com/Components/TimeField) |
| | `TimePicker` | Extension |
| | `BentoDSWeekDaysSelector` | Extension |
| Loading | `BentoDSAreaLoader` | [AreaLoader](https://developer.bento-ds.com/Components/AreaLoader) |
| | `BentoDSInlineLoader` | [InlineLoader](https://developer.bento-ds.com/Components/InlineLoader) |
| | `BentoDSProgressBar` | [ProgressBar](https://developer.bento-ds.com/Components/ProgressBar) |
| Modal | `BentoDSModal`, `ModalContainer` | [Modal](https://developer.bento-ds.com/Components/Modal) |
| | `BentoDSBottomSheet`, `ModalBottomSheetContainer` | Extension |
| | `Toast` | [Toast](https://developer.bento-ds.com/Components/Toast) |
| Navigation | `Breadcrumb` | [Breadcrumb](https://developer.bento-ds.com/Components/Breadcrumb) |
| | `Stepper` | [Stepper](https://developer.bento-ds.com/Components/Stepper) |
| | `BentoDSTabs` | [Tabs](https://developer.bento-ds.com/Components/Tabs) |
| Data visualization | `BentoDSLineChart` | [LineChart](https://developer.bento-ds.com/Components/LineChart) |
| Layout | `VSpace`, `HSpace`, `FSpace`, `Space` | [Layout](https://developer.bento-ds.com/Guides/layout) |

Not implemented yet: BarChart, DonutChart, Disclosure, Pagination, SliderField, Table, Tooltip.

## Icons

Besides the Bento DS icons (`R.drawable.ic_*`), the library ships about 1,400
[Phosphor](https://phosphoricons.com) icons in regular and fill weights (`R.drawable.ph_*`).
`PhosphorIcons` lists them by name and category and parses stored keys such as `drop-fill`.

## Licenses

- Phosphor icons: MIT, see [PHOSPHOR_LICENSE](PHOSPHOR_LICENSE).
- Lexend font: SIL Open Font License 1.1.
