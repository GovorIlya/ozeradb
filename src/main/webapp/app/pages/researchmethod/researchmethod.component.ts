import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
 import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
 import { Observable } from 'rxjs/Rx';

import { Researchmethod } from './researchmethod.model';
import { ResearchmethodService } from './researchmethod.service';
import { Principal } from '../../shared';

import {Factormethod, FactormethodService} from '../factormethod';
import {Unit} from '../../entities/unit';
import {UnitService} from '../../entities/unit';
import {RatingMethod} from '../../entities/rating-method';
import {RatingMethodService} from '../../entities/rating-method';

import {ResearchMethod} from '../../entities/research-method';
import {ResearchMethodService} from '../../entities/research-method';
import {Regions} from './regions.model';

@Component({
    selector: 'jhi-researchmethod',
    templateUrl: './researchmethod.component.html'
})
export class ResearchmethodComponent implements OnInit, OnDestroy {
    regions: Regions[] =[
        {Id:1, Name: 'Минская'},
        {Id:2, Name: 'Гомельская'},
        {Id:3, Name: 'Могилевская'},
        {Id:4, Name: 'Витебская'},
        {Id:5, Name: 'Гродненская'},
        {Id:6, Name: 'Брестская'}
    ];
    btnState:boolean;
    regionName: string;
    researchmethods: ResearchMethod[];
    factormethods: Factormethod[];
    ratingMethods:RatingMethod[];
    units: Unit[];
    unName:string;
  //  uResearchId: number;
    uFactorId: number;


    researchmethod: Researchmethod = new Researchmethod();

    currentAccount: any;
    eventSubscriber: Subscription;
    isSaving: Boolean;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private factorMethodService: FactormethodService,
        private ratingMethodService:RatingMethodService,
        private unitService:UnitService,
        private researchMethodService: ResearchMethodService,
        private researchmethodService: ResearchmethodService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.researchMethodService.query().subscribe(
            (res: HttpResponse<ResearchMethod[]>) => {
                this.researchmethods= res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.factorMethodService.query().subscribe(
            (res: HttpResponse<Factormethod[]>) => {
                this.factormethods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.ratingMethodService.query().subscribe(
            (res: HttpResponse<RatingMethod[]>) => {
                this.ratingMethods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.unitService.query().subscribe(
            (res: HttpResponse<Unit[]>) => {
                this.units = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.btnState=true;
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

        this.registerChangeInResearchmethods();
    }

    ngOnDestroy() {

        this.eventManager.destroy(this.eventSubscriber);
    }
    registerChangeInResearchmethods() {
        this.eventSubscriber = this.eventManager.subscribe('researchmethodListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
    onClickMe(uId:any) {
       // this.unName=this.units[uId].unitName;
        // this.uResearchId=this.units[uId].ratingMethod.id;

        for (let un of this.units) {
            for (let res of this.researchmethods) {
                if (un.id == uId && un.researchMethod.id==res.id) {
                    this.unName = un.unitName;
                    this.uFactorId=un.researchMethod.id;

                }
            }
        }
       // this.uFactorId=this.units[uId].researchMethod.id;


    }
    onClickRegion(regname: string){
        this.btnState=false;
        this.regionName=regname;


        this.unName = "";


    }
}
