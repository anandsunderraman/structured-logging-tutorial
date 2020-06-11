def searchResults = ctx.payload.hits.hits;

def orderIdMap = [:];
//denotes the time the watcher was triggerred
def currentTime = ctx.trigger['triggered_time'];
ctx.payload.currentTime = currentTime;

for (int i = 0; i < searchResults.size(); i++) {
    def search = searchResults[i]['_source'];
    def message = search.message;
    def orderId = null;
    def orderTracker = null;


    if ('Created new order' == message) {
        orderId = search.order.orderId;
        orderTracker = orderIdMap[orderId];
        if (orderTracker == null) {
            orderTracker = [:];
        }
        orderTracker['orderCreationTime'] =  search['@timestamp'];
        def diff = (currentTime.getMillis() - Instant.parse(orderTracker.orderCreationTime).toEpochMilli()) / 1000 ;
        orderTracker['timeElapsedInSec'] = diff;
        orderIdMap[orderId] = orderTracker;
    } else if ('Shipped order' == message) {
        orderId = search.shipment.orderId;
        orderTracker = orderIdMap[orderId];
        if (orderTracker == null) {
            orderTracker = [:];
        }
        orderTracker['shippingTime'] =  search['@timestamp'];
        orderIdMap[orderId] = orderTracker;
    }
}
ctx.payload.orderIdMap = orderIdMap;

String anomalies = '';
for (orderId in orderIdMap.keySet()) {
    def orderTracker = orderIdMap[orderId];
    def shippingSLA = 5;

    if (orderTracker.orderCreationTime!= null && orderTracker.shippingTime == null && orderTracker.timeElapsedInSec > shippingSLA) {
        def message = 'OrderId: ' +  orderId + ' Created At: ' + orderTracker['orderCreationTime'];
        anomalies = anomalies + message + '\n';
    }


}

ctx.payload.anomalies = anomalies;
return ctx.payload.anomalies.length() > 0;