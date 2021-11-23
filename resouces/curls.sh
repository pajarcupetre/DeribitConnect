
# get balance for user
curl --location --request GET 'http://localhost:9000/getAccountBalance?clientId=T3pjo8tj&clientSecret=b42_QSgzCBM-9Qzm9p5qe_ueEXlwzBrKcdDUw7GIZU0'
# add useMain false to get balance only from main account, by default is aggregated from all subaccounts


#get historic deposits and transactions
curl --location --request GET 'http://localhost:9000/getHistoricTransactions?clientId=T3pjo8tj&clientSecret=b42_QSgzCBM-9Qzm9p5qe_ueEXlwzBrKcdDUw7GIZU0'

# transfer to another account: this will fail if you don't pass a valid acccount
curl --location --request PUT 'http://localhost:9000/transferExternal' \
--header 'Content-Type: application/json' \
--data-raw '{
  "clientId": "T3pjo8tj",
  "clientSecret": "b42_QSgzCBM-9Qzm9p5qe_ueEXlwzBrKcdDUw7GIZU0",
  "currency": "BTC",
  "amount": 0.1,
  "externalAccount": "ppetre15second"
}'

# move between main account and subaccount
curl --location --request PUT 'http://localhost:9000/moveToSubAccount' \
--header 'Content-Type: application/json' \
--data-raw '{
  "clientId": "T3pjo8tj",
  "clientSecret": "b42_QSgzCBM-9Qzm9p5qe_ueEXlwzBrKcdDUw7GIZU0",
  "currency": "BTC",
  "amount": 0.1,
  "idSubAccount": 35186
}'