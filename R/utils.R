paste_with <- function(.sep) function(.char1, .char2) paste(.char1, .char2, sep = .sep)

"%_%" <- paste_with('_')

add_quotes <- function(x) paste0('\"', x, '\"')

add_class <- function(.data, .class) structure(.data, class = c(.class, class(.data)))

empty_element <- function(.tag, ...) {
  key_vals <- list(...)
  keys <- paste0(names(key_vals), "=")
  vals <- add_quotes(key_vals)
  do.call(paste, c(paste0('<', .tag, ' '), Map(paste0, keys, vals), '/>'))
}