#' Write a random forest to an XML file
#' 
#' This function writes a random forest to an XML file.
#' 
#' @param .model object of class \code{randomForest}
#' @param .file_path file path of XML file
#' @param .id used to set \code{id} attribute of root element
#' 
#' @export

write.random_forest <- function(.model, .file_path, .id = NULL) {
  assert_that(class(.model) == 'randomForest')
  
  if (is.null(.id)) start_tag <- '<random_forest>'
  else start_tag <- paste0('<random_forest id=', .add_quotes(.id), '>')
  
  write(start_tag, .file_path)
  
  ntree <- .model$ntree
  
  for (i in seq_len(ntree)) {
    .tree <- get_tree(.model, i)
    .nodes <- extract_nodes(.tree)
    .xml <- as.xml(.nodes, i)
    write.xml(.xml, .file_path)
  }
  
  write('</random_forest>', .file_path, append = TRUE)
}