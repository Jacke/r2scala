write.xml <- function(.xml, .file_path, .append = TRUE) {
  invisible(lapply(.xml, write, .file_path, append = .append))
}