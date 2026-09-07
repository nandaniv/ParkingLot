The UML needs to have service class in between orchestrating service and strategy class.


Req:
1. Park the vehicle
   vehicle enters: allot the spot, generate the ticket
2. unpark
   When it exits: calculate the price, process payment, free the spot
3. support multiple floors and multiple ways of alloting the spot
4. make the system thread safe
5. support multiple vehicle types and spot types