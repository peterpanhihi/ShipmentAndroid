# Shipment Tracking Service

Shipment tracking system is used for the shipping and the observing of product order on the move that can manage order on the network for real time.

##Features
* Online Shipment
* Online Track Shipment
* Android Application
* Web Application (use AngularJS)
* OAuth Authentication

##Use Cases
*   Customer
    * Reciever : ( Android Application )
        -  <i>Authenticate Not Required</i>
        ```
          I can check shipment status using shipment number.
        ```
    * Courier : ( Android Application )
        - <i>Authenticate Required</i>
        ```
          I can create shipment
          I can check all the shipment that I create
          I can cancel shipment
        ```
        - <i>Authenticate Not Required</i>
        ```
          I can check shipment status using shipment number.
          I can check the shipment cost by sending item information
        ```
* Delivery Person : ( Web Application ) 
    -   <i>Authenticate Required</i>
    ```
      I can edit shipment status
    ```

##Functions
* OAuth Request
  ```
  /google 
  /oauth2callback
  ```

* Customer
    ```
  	/shipments/calculate GET
    ```
	* Reciever
    ```
    /shipments/{id} GET
    ```
	* Courier
    ```
    /shipments GET
    /shipments/{id} GET, POST, DELETE
    ```
* Delivery Person

    ```
    /shipments GET
    /shipments/{id} GET, PUT
    ```

## API Definition

* Get all shipments
 
 
  | Request       	|
  | ------------- 	|
  | GET /shipments    |



  | Response JSON      	| Response XML       	|
  | ------------- 	|:-------------:	|
  | {"shipments": [{ … },{ … }]}   | coming soon|


[See more](https://github.com/ixistic/Shipment-Tracking-Service/wiki/API)

## Software Design
<i>coming soon</i>

## Other Documents

* [BDD](https://github.com/ixistic/Shipment-Tracking-Service/wiki/BDD) - Behavior-Driven Development


## Members

- Suwijak Chaipipat (5510545046) - Github: [Aunsuwijak](https://github.com/aunsuwijak)
- Veerapat Threeravipark (5510547022) - Github: [iXisTiC](https://github.com/ixistic)
- Juthamas Utamaphethai (5510546964) - Github: [PeterpanHiHi](https://github.com/peterpanhihi)

