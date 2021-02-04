
   initdb -D ./data -U postgres -E UTF8
> pg_ctl -D ./data -l logs start
> pg_ctl -D ./data -l logs stop

>psql -U postgres

	\?                    Help
	\list                 database list
	\connect <db> <usr>
	\dt                   tables list
	\d  <table>           describe table
	\q                    exit
	create database <db>;
	drop database <db>;