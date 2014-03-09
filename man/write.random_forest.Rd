\name{write.random_forest}
\alias{write.random_forest}
\title{Write a random forest to an XML file}
\usage{
  write.random_forest(.model, .file_path, .id = NULL,
    .progress = multiples_of(n))
}
\arguments{
  \item{.model}{object of class \code{randomForest}}

  \item{.file_path}{file path of XML file}

  \item{.id}{used to set \code{id} attribute of root
  element}

  \item{.progess}{a function with argument the index of the
  tree which is being written; used to track the progress
  of the \code{\link{write}} operation}
}
\description{
  This function writes a random forest to an XML file.
}

