\name{write.random_forest}
\alias{write.random_forest}
\title{Write a random forest to an XML file}
\usage{
  write.random_forest(.model, .file_path, .id = NULL)
}
\arguments{
  \item{.model}{object of class \code{randomForest}}

  \item{.file_path}{file path of XML file}

  \item{.id}{used to set \code{id} attribute of root
  element}
}
\description{
  This function writes a random forest to an XML file.
}

