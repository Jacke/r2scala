# get tree --------------------------------------------------------------------------------------------
get_tree.randomForest <- .get_tree(randomForest::getTree)

# coerce a tree to a list of nodes --------------------------------------------------------------------
as.nodes.randomForest_tree <- function(.data) {
  
  nodes <- list()
  properties <- c('split_var', 'split_point', 'prediction')
  .names <- c('metric', 'threshold', 'prediction', 'depth', 'width', 'leaf_node')
  
  loop <- function(index, depth, width) {    
    node_row <- .data[index, ]
    leaf_node <- node_row['status'] == -1
    node <- c(node_row[properties], depth, width, as.numeric(leaf_node))
    nodes[[index]] <<- structure(node, names = .names)
    
    if (leaf_node) return(invisible())
    
    left_index <- node_row['left_daughter']
    right_index <- node_row['right_daughter']
    
    list(
      loop(left_index, depth + 1, 2 * width), 
      loop(right_index, depth + 1, 2 * width + 1)
    )
  }
  
  loop(1, 0, 0)
  add_class(nodes, 'nodes')
}

# coerce a list of nodes to xml -----------------------------------------------------------------------
as.xml.nodes <- function(.data, .id = NULL) {
  start_tag <- start_id_tag('tree', .id)
  empty_node <- function(.node) do.call(empty_element_tag, c('node', as.list(.node)))
  xml_nodes <- c(
    start_tag,
    paste(lapply(.data, empty_node), sep = '\n'),
    '</tree>'
  )
  add_class(xml_nodes, 'xml')
}

#' Write a random forest to an XML file
#' 
#' This function writes a random forest to an XML file.
#' 
#' @usage write.random_forest(.model, .file_path, .id = NULL, .progress = multiples_of(n))
#' @param .model object of class \code{randomForest}
#' @param .file_path file path of XML file
#' @param .id used to set \code{id} attribute of root element
#' @param .progess a function with argument the index of the tree which is being written; used to track
#' the progress of the \code{\link{write}} operation
#' @export
write.random_forest <- function(.model, .file_path, .id = NULL, .progress = NULL) {
  assert_that(class(.model) == 'randomForest')
  start_tag <- start_id_tag('random_forest', .id)
  
  if (is.null(.progress)) .progress <- function(...) NULL
  
  write(start_tag, .file_path)
  
  ntree <- .model$ntree
  for (i in seq_len(ntree)) {
    tree <- get_tree(.model, i)
    nodes <- as.nodes(tree)
    xml_nodes <- as.xml(nodes, i)
    write.xml(xml_nodes, .file_path)
    .progress(i)
  }
  
  write('</random_forest>', .file_path, append = TRUE)
}

#' @export
multiples_of <- function(n) function(i) if (i %% n == 0) cat(i, ' ')