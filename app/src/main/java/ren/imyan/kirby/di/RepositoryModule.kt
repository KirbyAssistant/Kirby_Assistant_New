package ren.imyan.kirby.di

import org.koin.dsl.module
import ren.imyan.kirby.repository.GameRepository
import ren.imyan.kirby.repository.HomeRepository

val repositoryModule = module{
    single {
        HomeRepository()
    }
    single {
        GameRepository()
    }
}