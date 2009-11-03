package com.renaissance.etl
import groovy.sql.Sql

def context = AppContextManager.getAppContext()
def sql = context[AppContextManager.MYSQL]
println sql.connection.catalog
sql.call("call update_avg30()")
