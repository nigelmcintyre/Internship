public class getAscentItems {
    
    private final Account acc;
    public Asset AssetItems {get;set;}
    public getAscentItems(ApexPages.StandardController stdController) {
        this.acc = (Account)stdController.getRecord();
        AssetItems = new Asset();
    }
       
        public Integer getAssetCount() {
        Integer aCnt = [select count() FROM PBSI__Serial_Number_Tracker__c 
        where PBSI__Serial_Number__c like '04%' AND PBSI__Ship_Location__r.PBSI__Account__c = :acc.ID];
        return aCnt;    
    }    
        
    public List<PBSI__Serial_Number_Tracker__c> getAscentAssetLines() {
        List<PBSI__Serial_Number_Tracker__c> qlisAsset = [SELECT Id, PBSI__Item__r.name,PBSI__Serial_Number__c,PBSI__Date_Shipped__c,PBSI__Ship_Location__c
        FROM PBSI__Serial_Number_Tracker__c 
        where PBSI__Serial_Number__c like '04%' AND PBSI__Ship_Location__r.PBSI__Account__c = :acc.ID];
        return qlisAsset;        
    }
    public List<Asset> getAssetItems(){
        List<Asset> ai = [SELECT SystemID__c, Password__c, License_Type__c, Managed_CNE__c, Market_Data_Decoder_Pack__c, Transaction_Protocol_Decoder_Pack__c, Enterprise_Decoder_Pack__c, Management_Reports__c, Live_Dashboards__c, Lens__c, Comments__c, Network_Interfaces__c, Network_Capture_and_Analysis__c, License_Validity__c, user_specified_end_time__c
                          FROM Asset ];
            return ai;
    }
    
    

    public List<Account> getCustomerNames() {
        List<Account> qlisAccount = [SELECT Id, name 
        FROM Account 
        where Account.Name like 'N%' ];
        return qlisAccount;        
    } 
       
    public Integer getCustomerCount() {
        Integer custCnt = [select count() FROM account];
        return custCnt;    
    }  
    public pagereference saveAssets()
    {
        update AssetItems;
        return null;
    }

}