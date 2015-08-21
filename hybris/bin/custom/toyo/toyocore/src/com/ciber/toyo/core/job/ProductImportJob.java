package com.ciber.toyo.core.job;

import com.ciber.toyo.core.model.TiresImportCronJobModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by jpal on 21/08/15.
 */
public class ProductImportJob extends AbstractJobPerformable<TiresImportCronJobModel>{
    @Autowired
    private ProductService productService;

    @Override
    public PerformResult perform(TiresImportCronJobModel paramT) {
        System.out.println("Performing product import");
        String productCode = "26388000";

        // this method assumes that there is only one product with the code -
        // you need to use different method (normally you have 2 products: online and staged, I've removed the staged one to make it work)
        ProductModel productModel = productService.getProductForCode(productCode);

        productModel.setDescription("TOYO TIRES PRODUCT DESCRIPTION IMPORT "+new Date());
        modelService.save(productModel);
        PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        return result;
    }
}
