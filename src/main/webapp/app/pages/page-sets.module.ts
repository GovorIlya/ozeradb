import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { OzeradbDescriptionModule } from './description/description.module';
import { OzeradbKaptaModule } from './kapta/kapta.module';
import { OzeradbProblemtypesModule } from './problemtypes/problemtypes.module';
import { OzeradbResearchmethodModule } from './researchmethod/researchmethod.module';
import { OzeradbTeachstateModule } from './teachstate/teachstate.module';
import { OzeradbFactormethodModule } from './factormethod/factormethod.module';
/* jhipster-needle-add-pageset-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        OzeradbDescriptionModule,
        OzeradbKaptaModule,
        OzeradbProblemtypesModule,
        OzeradbResearchmethodModule,
        OzeradbTeachstateModule,
        OzeradbFactormethodModule,
        /* jhipster-needle-add-pageset-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OzeradbPageSetsModule {}
