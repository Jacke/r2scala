extract_nodes <- function(.model, ...) {
  UseMethod("extract_nodes")
}

extract_nodes.randomForest_tree <- function(.data) {
  
  nodes <- list()
  properties <- c('split_var', 'split_point', 'prediction')
  .names <- c('metric', 'threshold', 'prediction', 'depth', 'width', 'leaf_node')
  
  loop <- function(index, depth, width) {    
    node_row <- .data[index, ]
    leaf_node <- node_row['status'] == -1
    node <- c(node_row[properties], depth, width, as.numeric(leaf_node))
    nodes[[index]] <<- structure(node,
                                 names = .names,
                                 class = c('node', class(node)))
    
    if (leaf_node) return(invisible())
    
    left_index <- node_row['left_daughter']
    right_index <- node_row['right_daughter']
    
    list(
      loop(left_index, depth + 1, 2 * width), 
      loop(right_index, depth + 1, 2 * width + 1)
    )
  }
  
  loop(1, 0, 0)
  .add_class(nodes, 'nodes')
}