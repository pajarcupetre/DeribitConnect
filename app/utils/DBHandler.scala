package utils

import models.AccountBalanceInfo
import play.api.Configuration

import java.sql.{DriverManager, ResultSet}

trait DBHandler {

  def executeUpdate(query: String, config: Configuration): Int = {
    val url =  config.get[String]("postgresql.url")
    // Setup the connection
    val conn = DriverManager.getConnection(url)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)

      // Execute Query
      statement.executeUpdate(query)
    }
    finally {
      conn.close
    }
  }

  def updateAccountInfo(accountBalanceInfo: AccountBalanceInfo, config: Configuration): Seq[Int] = {
    accountBalanceInfo.currentBalance.map(
      currencyHolding => {
        val newAmount = currencyHolding.totalAmount
        val newReserved = currencyHolding.reservedAmount
        val currency = currencyHolding.currencyName
        val sqlString = s"INSERT INTO balance (username, currency, totalAmount, reservedAmount) VALUES(\'${accountBalanceInfo.userName}\', \'${currency}\', ${newAmount}, ${newReserved})" +
          s" ON CONFLICT (username, currency) DO UPDATE SET totalAmount = ${newAmount}, reservedAmount = ${newReserved} ;"
        executeUpdate(sqlString, config)
      }
    )
  }

}

object DBHandler extends DBHandler
