as.xml <- function(.data, ...) {
  UseMethod('as.xml')
}

as.xml.node <- function(.data) {
  atts <- lapply(c(keys = names, vals = unname), function(f) f(.data))
  combine <- function(key, val) paste(key, .add_quotes(val), sep = '=')
  .args <- do.call(Map, list(combine, atts$keys, atts$vals))
  .args$sep <- ' '
  paste('<node', do.call(paste, .args), '/>', sep = ' ')
}

as.xml.nodes <- function(.data, .id = NULL) {
  if (is.null(.id)) start_tag <- '<tree>'
  else start_tag <- paste0('<tree id=', .add_quotes(.id), '>')
  .xml <- c(
    start_tag,
    paste(lapply(.data, as.xml.node), sep = '\n'),
    '</tree>'
  )
  .add_class(.xml, 'xml')
}