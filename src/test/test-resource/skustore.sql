call dtopt.scenarioRunResultExtPrepAndPost(#runid#, 'PREP', 0);
select
    srr.scenariorunresultid s1,
    case when NOT (sr.isFranchiseRun = 0 and sr.viewPriceChange = 1 and (sr.status = 16   OR sr.status = 7  OR sr.status = 4)) 
         then '' 
         else case when (srp.isprepriced+srr.islocked+(ABS(srr.isuserselected-1))) =0 
                   then 'menuSRRViewPriceChanges,' 
                   else 'menuSRRViewPriceChangesError,' 
                   end  
         END || 'menuScenarioRunResultMRA,menuScenarioRunResultRelationships,menuExport' s3,
    rtrim(char('deAggr_ID')) || '`' || rtrim(char('0')) || '`' || rtrim(char('ScenarioRunID')) || '`' || rtrim(char(srr.ScenarioRunID)) a0,
    srp.name c0,
    srp.UPC c1,
    srl.zone c2,
    srl.name c3,
    srp.VolOfMeasure c4,
    srp.UnitOfMeasure c5,
    srr.InitMultiple c6,
    srr.InitUnitPrice * srr.InitMultiple c7,
    srr.optMultiple c8,
    srr.OptUnitPrice * srr.optMultiple c9,
    srr.OptModePrice c10,
    srr.OverOptMultiple c11,
    srr.OverOptUnitPrice * coalesce(srr.overoptmultiple,1) c12,
    (CASE WHEN srr.InitUnitPrice = 0 THEN 0 ELSE (srr.OptUnitPrice - srr.InitUnitPrice) / srr.InitUnitPrice END) c13,
    CASE WHEN srr.OptModePrice != 0 THEN (srr.OptUnitPrice - srr.OptModePrice)/srr.OptModePrice END c14,
    COALESCE(xc.SuggestedMultiple,1) c15,
    xc.UnitPrice * COALESCE(xc.SuggestedMultiple,1) c16,
    case when xc.unitprice != 0 then ( srr.optunitprice /double(xc.unitprice)  ) -1  end c17,
    srrext.Competitor1Name c18,
    srrext.CompPrice1 c19,
    srrext.Competitor2Name c20,
    srrext.CompPrice2 c21,
    srrext.Competitor3Name c22,
    srrext.CompPrice3 c23,
    srr.Cost c24,
    srr.InitUnitVol c25,
    srr.OptUnitVol c26,
    (CASE WHEN srr.InitUnitVol = 0 THEN 0 ELSE (srr.OptUnitVol - srr.InitUnitVol) / srr.InitUnitVol END) c27,
    case when srr.InitGMPctDenom != 0 then srr.InitGrossMargin/srr.InitGMPctDenom end c28,
    case when srr.OptGMPctDenom != 0 then srr.OptGrossMargin/srr.OptGMPctDenom end c29,
    srr.InitGrossMargin c30,
    srr.OptGrossMargin c31,
    DTOPT.GETUNITGM(1,srr.InitUnitPrice,srr.OldCost,srr.VatRate,srp.EGMFactor) c32,
    DTOPT.GETUNITGM(1,srr.OptUnitPrice,srr.Cost,srr.VatRate,srp.EGMFactor) c33,
    srr.InitRev c34,
    srr.OptRev c35,
    (srr.OptRev - srr.InitRev) c36,
    (case when srr.InitRev != 0 then srr.OptRev/srr.InitRev - 1 end) c37,
    srp.LineGroup c38,
    srp.IsPrePriced c39,
    srr.currentretailmultiple c40,
    srr.currentretailmultiple * srr.currentretailprice c41
FROM DTOpt.ScenarioRunResult srr
INNER join DTOpt.ScenarioRunProduct srp             
on srp.scenariorunid = srr.scenariorunid             
and srp.productid = srr.productid
inner join dtopt.scenariorunbrand srb             
on srp.productbrandid = srb.productbrandid             
and srb.scenariorunid = srp.scenariorunid
inner join dtopt.scenariorundemandgroup pdg             
on pdg.scenariorunid = srp.scenariorunid             
and pdg.productdemandgroupid = srp.productdemandgroupid
inner join dtopt.scenariorunlocation srl             
on srl.scenariorunid = srr.scenariorunid             
and srr.locationid = srl.locationid
left outer join dtopt.scenarioRun sr on sr.scenarioRunId =  #runid#
left outer join DTopt.productcategory pc       
on pc.productcategoryid = srp.productcategoryid                 
LEFT OUTER JOIN DTOpt.CompPrice xc 
ON xc.LocationID = srr.LocationID 
AND xc.ProductID = srr.ProductID 
AND xc.CompetitorID = -1 
AND xc.IsDeleted = 0                  
left outer join DTOpt.Competitor c              
ON c.CompetitorID = xc.CompetitorID           
LEFT OUTER JOIN SESSION.VWsrrc srrext                 
ON srrext.ScenarioRunResultId = srr.ScenarioRunResultId         
left outer join dtopt.ScenarioRunProductZone srpz             
on srpz.ScenarioRunID = srr.ScenarioRunID             
and srpz.ProductID = srr.ProductID             
and srpz.ZoneID = srl.ZoneID          
left outer join dtopt.scenariorunlinemultiplier srlm             
on srlm.Line2 = srp.LineGroup             
and srlm.ScenarioRunId = srp.ScenarioRunID             
and srlm.zoneid = srl.zoneid   
full outer join         (               
select srpm.SCENARIORUNID, srpm.PRODUCTID2,            
srp1.UPC as upcDriver, srpm.MULTIPLIER, srpm.RELATIONSHIPTYPE,           
srpm.zoneid, srpm.source           
from dtopt.SCENARIORUNPRODUCTMULTIPLIER srpm           
inner join dtopt.SCENARIORUNPRODUCT srp1              
on srpm.PRODUCTID1 = srp1.PRODUCTID              
and srpm.SCENARIORUNID = srp1.SCENARIORUNID           
where srpm.SCENARIORUNID = #runid#) as M           
on M.productid2 = srp.PRODUCTID           
and M.ScenarioRunID = srp.ScenarioRunID           
and M.zoneid = srl.zoneid
where srr.ScenarioRunID = #runid#         and 1 = 1              
fetch first 201 rows only
optimize for 201 rows
WITH UR;
call dtopt.scenarioRunResultExtPrepAndPost(#runid#, 'POST', 0);