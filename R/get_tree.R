get_tree <- function(.model, ...) UseMethod('get_tree')

.get_tree <- function(.local) {
  function(.model, ...) {
    .data <- .local(.model, ...)
    colnames(.data) <- gsub(' ', '_', tolower(colnames(.data)))
    add_class(.data, class(.model) %_% 'tree')
  }
}