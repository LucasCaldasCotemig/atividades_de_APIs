package com.lucao.rickandmorty.model

data class InfoDto(
  val count: Int,
  val pages: Int,
  val next: String?,
  val prev: String?
    )
