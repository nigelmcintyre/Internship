public class assetSummary {
    public account acc;
    
    public String name {get;set;}
    public integer quantity {get;set;}
    public assetSummary(string n,integer q){
        this.quantity=q;
        this.name=n;
    }
    public assetSummary(ApexPages.StandardController controller){
        this.acc = (Account)controller.getRecord();
    }
    public List<assetSummary> assetList = new List<assetSummary>();
    public list<assetSummary> getListOut(){
        AggregateResult[] ar = [SELECT PBSI__Item__r.name name, count(id) FROM PBSI__Serial_Number_Tracker__c WHERE PBSI__Account__c = :acc.ID GROUP BY PBSI__Item__r.name];
        for (AggregateResult resultList : ar){
            assetList.add(new assetSummary(String.valueOf(resultList.get('name')),integer.valueOf(resultList.get('quantity'))));
        }
        return assetList;
    }
}
