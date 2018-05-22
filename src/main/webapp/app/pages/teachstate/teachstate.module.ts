import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OzeradbSharedModule } from '../../shared';
import {
    TeachstateService,
    TeachstateComponent,
    TeachstateRoute,
} from './';
import {ImageService} from '../../entities/image';
import {UnitService} from '../../entities/unit';

const PAGE_SET_STATES = [
    ...TeachstateRoute,
];

@NgModule({
    imports: [
        OzeradbSharedModule,
        RouterModule.forRoot(PAGE_SET_STATES, { useHash: true })
    ],
    declarations: [
    TeachstateComponent,
],
    entryComponents: [
    TeachstateComponent,
],
    providers: [
    TeachstateService,
        ImageService,
        UnitService
],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class OzeradbTeachstateModule {}
