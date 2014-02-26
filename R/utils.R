.paste_with <- function(.sep) {
  function(.char1, .char2) {
    paste(.char1, .char2, sep = .sep)
  }
}

"% %" <- .paste_with(' ')
"%_%" <- .paste_with('_')
"%=%" <- .paste_with('=')

.add_quotes <- function(x) {
  paste0('\"', x, '\"')
}

.add_class <- function(.data, .class) {
  structure(.data, class = c(.class, class(.data)))
}