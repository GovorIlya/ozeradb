import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { OzeradbUnitModule } from './unit/unit.module';
import { OzeradbUnitDescriptionModule } from './unit-description/unit-description.module';
import { OzeradbImageModule } from './image/image.module';
import { OzeradbResearchMethodModule } from './research-method/research-method.module';
import { OzeradbTypesProblemsModule } from './types-problems/types-problems.module';
import { OzeradbRatingMethodModule } from './rating-method/rating-method.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        OzeradbUnitModule,
        OzeradbUnitDescriptionModule,
        OzeradbImageModule,
        OzeradbResearchMethodModule,
        OzeradbTypesProblemsModule,
        OzeradbRatingMethodModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OzeradbEntityModule {}
