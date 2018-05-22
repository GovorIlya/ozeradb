import { NgModule, CUSTOM_ELEMENTS_SCHEMA, ApplicationRef } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


import { OzeradbSharedModule } from '../../shared';
import {
    KaptaService,
    KaptaComponent,
    KaptaRoute,
} from './';
import {AgmCoreModule} from '@agm/core';


const PAGE_SET_STATES = [
    ...KaptaRoute,
];

@NgModule({
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,

        OzeradbSharedModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyCJ014oXn2nPUrDoswuMpDCKGKVqNslwP4',
            libraries:['places']
        }),
        RouterModule.forRoot(PAGE_SET_STATES, { useHash: true })


    ],
    declarations: [
    KaptaComponent,
],
    entryComponents: [
    KaptaComponent,
],
    providers: [
    KaptaService,
],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class OzeradbKaptaModule {}
